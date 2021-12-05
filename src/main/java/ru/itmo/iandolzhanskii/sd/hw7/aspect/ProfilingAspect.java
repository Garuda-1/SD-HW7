package ru.itmo.iandolzhanskii.sd.hw7.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProfilingAspect {

    private final Profiler profiler;

    public ProfilingAspect(Profiler profiler) {
        this.profiler = profiler;
    }

    @Around("execution(* ru.itmo.iandolzhanskii.sd.hw7.distribution..*(..)) && !within(ru.itmo.iandolzhanskii.sd.hw7.aspect..*)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Signature signature = proceedingJoinPoint.getSignature();
        if (!signature.getDeclaringType().getPackageName().startsWith(profiler.getTargetPackage())) {
            return proceedingJoinPoint.proceed();
        }

        String methodName = signature.getDeclaringTypeName() + "#" + signature.getName();
        long startNanos = System.nanoTime();
        try {
            return proceedingJoinPoint.proceed();
        } finally {
            long latencyNanos = System.nanoTime() - startNanos;
            profiler.register(methodName, latencyNanos);
        }
    }
}
