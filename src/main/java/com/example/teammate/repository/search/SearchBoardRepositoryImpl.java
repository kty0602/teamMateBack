package com.example.teammate.repository.search;

import com.example.teammate.entity.Board;
import com.example.teammate.entity.QBoard;
import com.example.teammate.entity.QReply;
import com.example.teammate.entity.QUser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {
    public SearchBoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public Board search1() {
        log.info("search1.............");

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QUser user = QUser.user;

        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(user).on(board.bUser.eq(user));
        jpqlQuery.leftJoin(reply).on(reply.rBoard.eq(board));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, user.idx, reply.count());

        tuple.groupBy(board);

        log.info("-----------------------------------");
        log.info(tuple);
        log.info("-----------------------------------");

        List<Tuple> result = tuple.fetch();
        log.info(result);

        return null;
    }

    @Override
    public Page<Object[]> searchPage(String type, Pageable pageable) {
        log.info("searchPage.........................");

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QUser user = QUser.user;


        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(user).on(board.bUser.eq(user));
        // 서브쿼리를 사용하여 삭제되지 않은 댓글 수를 계산
        JPQLQuery<Long> subQuery = JPAExpressions.select(reply.count())
                .from(reply)
                .where(reply.rBoard.eq(board).and(reply.rdelete.ne(false)));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, user, subQuery);

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = board.idx.gt(0L);

        // 글이 삭제된 경우 포함시키지 않음
        booleanBuilder.and(expression)
                .and(board.bdelete.ne(false));


        booleanBuilder.and(expression);

        if (type != null) {
            String[] conditions = type.split(":");
            BooleanBuilder conditionBuilder = new BooleanBuilder();
            String field = conditions[0];
            String value = conditions[1];
            switch (field) {
                case "n":
                    conditionBuilder.or(user.name.containsIgnoreCase(value));
                    break;
                case "t":
                    conditionBuilder.or(board.btitle.containsIgnoreCase(value));
                    break;
            }
            booleanBuilder.and(conditionBuilder);
        }

        tuple.where(booleanBuilder);

        Sort sort = pageable.getSort();

        sort.forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();

            PathBuilder orderByExpression = new PathBuilder(Board.class, "board");
            tuple.orderBy(new OrderSpecifier<>(direction, orderByExpression.get(prop)));
        });

        tuple.groupBy(board, user);

        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        log.info(result);

        long count = tuple.fetchCount();
        log.info("COUNT: " + count);

        return new PageImpl<>(
                result.stream().map(Tuple::toArray).collect(Collectors.toList()), pageable, count
        );
    }


}
