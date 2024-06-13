package com.example.teammate.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTeamReply is a Querydsl query type for TeamReply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeamReply extends EntityPathBase<TeamReply> {

    private static final long serialVersionUID = 553364221L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTeamReply teamReply = new QTeamReply("teamReply");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    //inherited
    public final DatePath<java.time.LocalDate> modDate = _super.modDate;

    //inherited
    public final DatePath<java.time.LocalDate> regDate = _super.regDate;

    public final StringPath trcontent = createString("trcontent");

    public final BooleanPath trdelete = createBoolean("trdelete");

    public final QTeam trTeam;

    public final QUser trUser;

    public QTeamReply(String variable) {
        this(TeamReply.class, forVariable(variable), INITS);
    }

    public QTeamReply(Path<? extends TeamReply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTeamReply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTeamReply(PathMetadata metadata, PathInits inits) {
        this(TeamReply.class, metadata, inits);
    }

    public QTeamReply(Class<? extends TeamReply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.trTeam = inits.isInitialized("trTeam") ? new QTeam(forProperty("trTeam"), inits.get("trTeam")) : null;
        this.trUser = inits.isInitialized("trUser") ? new QUser(forProperty("trUser")) : null;
    }

}

