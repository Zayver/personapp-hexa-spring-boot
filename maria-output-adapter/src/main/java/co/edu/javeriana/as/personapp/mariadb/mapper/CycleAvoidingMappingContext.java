package co.edu.javeriana.as.personapp.mariadb.mapper;

import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Qualifier;
import org.mapstruct.TargetType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.IdentityHashMap;

public class CycleAvoidingMappingContext {
    private final IdentityHashMap<Object, Object> knownInstances = new IdentityHashMap<>();

    @BeforeMapping
    public <T> T getMappedInstance(Object source,
                                   @TargetType Class<T> targetType) {
        return targetType.cast(knownInstances.get(source));
    }

    @BeforeMapping
    public void storeMappedInstance(Object source,
                                    @MappingTarget Object target) {
        knownInstances.put(source, target);
    }

    @Qualifier // make sure that this is the MapStruct qualifier annotation
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface DoIgnore {
    }
}
