package com.example.teammate.repository.search;

import com.example.teammate.dto.PageRequestDTO;
import com.example.teammate.entity.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.*;
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
public class SearchTeamRepositoryImpl extends QuerydslRepositorySupport implements SearchTeamRepository {


    public SearchTeamRepositoryImpl() { super(Team.class); }


    @Override
    public Page<Object[]> getSearch(PageRequestDTO requestDTO, Pageable pageable) {

        QTeam team = QTeam.team;
        QUser user = QUser.user;
        QTeamReply teamReply = QTeamReply.teamReply;

        JPQLQuery<Team> jpqlQuery = from(team);
        jpqlQuery.leftJoin(user).on(team.tUser.eq(user));


        JPQLQuery<Long> subQuery = JPAExpressions.select(teamReply.count())
                .from(teamReply)
                .where(teamReply.trTeam.eq(team).and(teamReply.trdelete.ne(false)));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(team, user, subQuery);

        String[] ktypeArray = requestDTO.getKtypeArray();
        String search = requestDTO.getSearch();

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression1 = team.idx.gt(0L);

        // 글이 삭제된 경우 포함시키지 않음
        booleanBuilder.and(expression1)
                .and(team.tdelete.ne(false));


        booleanBuilder.and(expression1);

        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if (search != null) {
            String[] conditions = search.split(":");
            String field = conditions[0];
            String value = conditions[1];
            switch (field) {
                case "n":
                    conditionBuilder.or(user.name.containsIgnoreCase(value));
                    break;
                case "t":
                    conditionBuilder.or(team.ttitle.containsIgnoreCase(value));
                    break;
            }
        }
        if (ktypeArray != null) {
            for (String k : ktypeArray) {
                BooleanExpression ktypeMatch = Expressions.stringTemplate(
                        "FUNCTION('FIND_IN_SET', {0}, {1})", Expressions.constant(k), team.tskill).gt(String.valueOf(0));
                conditionBuilder.or(ktypeMatch);
            }

        }

        booleanBuilder.and(conditionBuilder);

        tuple.where(booleanBuilder);

        Sort sort = pageable.getSort();

        sort.forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();

            PathBuilder orderByExpression = new PathBuilder(Team.class, "team");
            tuple.orderBy(new OrderSpecifier<>(direction, orderByExpression.get(prop)));
        });

        tuple.groupBy(team, user);

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
