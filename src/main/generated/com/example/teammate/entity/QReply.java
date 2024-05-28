package com.example.teammate.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReply is a Querydsl query type for Reply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReply extends EntityPathBase<Reply> {

    private static final long serialVersionUID = 658940762L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReply reply = new QReply("reply");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    //inherited
    public final DatePath<java.time.LocalDate> modDate = _super.modDate;

    public final QBoard rBoard;

    public final StringPath rcontent = createString("rcontent");

    public final BooleanPath rdelete = createBoolean("rdelete");

    //inherited
    public final DatePath<java.time.LocalDate> regDate = _super.regDate;

    public final QUser rUser;

    public QReply(String variable) {
        this(Reply.class, forVariable(variable), INITS);
    }

    public QReply(Path<? extends Reply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReply(PathMetadata metadata, PathInits inits) {
        this(Reply.class, metadata, inits);
    }

    public QReply(Class<? extends Reply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.rBoard = inits.isInitialized("rBoard") ? new QBoard(forProperty("rBoard"), inits.get("rBoard")) : null;
        this.rUser = inits.isInitialized("rUser") ? new QUser(forProperty("rUser")) : null;
    }

}

