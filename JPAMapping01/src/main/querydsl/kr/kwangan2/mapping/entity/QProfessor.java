package kr.kwangan2.mapping.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProfessor is a Querydsl query type for Professor
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProfessor extends EntityPathBase<Professor> {

    private static final long serialVersionUID = -1714437004L;

    public static final QProfessor professor = new QProfessor("professor");

    public final NumberPath<Long> pid = createNumber("pid", Long.class);

    public final StringPath professorName = createString("professorName");

    public final ListPath<Student, QStudent> studentList = this.<Student, QStudent>createList("studentList", Student.class, QStudent.class, PathInits.DIRECT2);

    public QProfessor(String variable) {
        super(Professor.class, forVariable(variable));
    }

    public QProfessor(Path<? extends Professor> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProfessor(PathMetadata metadata) {
        super(Professor.class, metadata);
    }

}

