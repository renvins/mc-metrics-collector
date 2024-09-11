package org.github.mcmetricscollector.security;

import com.google.common.collect.Lists;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.github.mcmetricscollector.security.objects.Role;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

@Aspect
@Component
public class PermissionChecker {

    @Around("@annotation(RequiresPermission)")
    Object checkPermission(ProceedingJoinPoint joinPoint) throws Throwable {
        PrincipalHolder principalHolder = PrincipalHolder.getInstance();
        var principal = principalHolder.getPrincipal();
        if (principal == null) {
            throw new SecurityException("No principal found");
        }

        var roles = principal.roles();
        var requires = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(RequiresPermission.class);

        if (requires == null) {
            return joinPoint.proceed();
        }

        var requiredPermissions = Lists.newArrayList(requires.value());
        var flattenedRoles = roles.stream()
                .map(Role::getPermissions)
                .flatMap(Collection::stream)
                .toList();

        requiredPermissions.removeAll(flattenedRoles);
        if (CollectionUtils.isEmpty(requiredPermissions)) {
            return joinPoint.proceed();
        }

        throw new SecurityException(requires.errorMessage());
    }

}
