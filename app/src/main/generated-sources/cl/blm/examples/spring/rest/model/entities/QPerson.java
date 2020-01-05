package cl.blm.examples.spring.rest.model.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPerson is a Querydsl query type for Person
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPerson extends EntityPathBase<Person> {

    private static final long serialVersionUID = -1864714097L;

    public static final QPerson person = new QPerson("person");

    public final StringPath address = createString("address");

    public final StringPath email = createString("email");

    public final StringPath fullName = createString("fullName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath idNumber = createString("idNumber");

    public final CollectionPath<Phone, QPhone> phones = this.<Phone, QPhone>createCollection("phones", Phone.class, QPhone.class, PathInits.DIRECT2);

    public QPerson(String variable) {
        super(Person.class, forVariable(variable));
    }

    public QPerson(Path<? extends Person> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPerson(PathMetadata metadata) {
        super(Person.class, metadata);
    }

}

