package kr.bbaa.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1118592472L;

    public static final QMember member = new QMember("member1");

    public final ListPath<kr.bbaa.board.entity.Board, kr.bbaa.board.entity.QBoard> boardList = this.<kr.bbaa.board.entity.Board, kr.bbaa.board.entity.QBoard>createList("boardList", kr.bbaa.board.entity.Board.class, kr.bbaa.board.entity.QBoard.class, PathInits.DIRECT2);

    public final ComparablePath<Character> enabled = createComparable("enabled", Character.class);

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final ListPath<kr.bbaa.reply.entity.Reply, kr.bbaa.reply.entity.QReply> replyList = this.<kr.bbaa.reply.entity.Reply, kr.bbaa.reply.entity.QReply>createList("replyList", kr.bbaa.reply.entity.Reply.class, kr.bbaa.reply.entity.QReply.class, PathInits.DIRECT2);

    public final EnumPath<kr.bbaa.member.domain.Role> role = createEnum("role", kr.bbaa.member.domain.Role.class);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

