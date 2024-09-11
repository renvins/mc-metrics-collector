package org.github.mcmetricscollector.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to require a role to access a method.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPermission {

    /**
     * The role required to access the method.
     */
    String[] value();

    String errorMessage() default "You do not have permission to access this resource.";

    String PUSH_PERMISSION = "push";

    String IAM_PERMISSION = "iamAll";

}
