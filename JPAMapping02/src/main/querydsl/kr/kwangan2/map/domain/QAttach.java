package kr.kwangan2.map.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAttach is a Querydsl query type for Attach
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttach extends EntityPathBase<Attach> {

    private static final long serialVersionUID = -443147667L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAttach attach = new QAttach("attach");

    public final QBoard board;

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final StringPath uuid = createString("uuid");

    public QAttach(String variable) {
        this(Attach.class, forVariable(variable), INITS);
    }

    public QAttach(Path<? extends Attach> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAttach(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAttach(PathMetadata metadata, PathInits inits) {
        this(Attach.class, metadata, inits);
    }

    public QAttach(Class<? extends Attach> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
    }

}

