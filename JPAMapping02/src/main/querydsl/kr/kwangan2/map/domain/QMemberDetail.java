package kr.kwangan2.map.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberDetail is a Querydsl query type for MemberDetail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberDetail extends EntityPathBase<MemberDetail> {

    private static final long serialVersionUID = -1461640781L;

    public static final QMemberDetail memberDetail = new QMemberDetail("memberDetail");

    public final StringPath addr = createString("addr");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final NumberPath<Long> mid = createNumber("mid", Long.class);

    public final StringPath name = createString("name");

    public QMemberDetail(String variable) {
        super(MemberDetail.class, forVariable(variable));
    }

    public QMemberDetail(Path<? extends MemberDetail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberDetail(PathMetadata metadata) {
        super(MemberDetail.class, metadata);
    }

}

