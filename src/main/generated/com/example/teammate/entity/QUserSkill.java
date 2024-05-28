package com.example.teammate.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserSkill is a Querydsl query type for UserSkill
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserSkill extends EntityPathBase<UserSkill> {

    private static final long serialVersionUID = 1069706678L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserSkill userSkill = new QUserSkill("userSkill");

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final QSkill sSkill;

    public final QUser sUser;

    public QUserSkill(String variable) {
        this(UserSkill.class, forVariable(variable), INITS);
    }

    public QUserSkill(Path<? extends UserSkill> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserSkill(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserSkill(PathMetadata metadata, PathInits inits) {
        this(UserSkill.class, metadata, inits);
    }

    public QUserSkill(Class<? extends UserSkill> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.sSkill = inits.isInitialized("sSkill") ? new QSkill(forProperty("sSkill")) : null;
        this.sUser = inits.isInitialized("sUser") ? new QUser(forProperty("sUser")) : null;
    }

}

