package ru.itmo.iandolzhanskii.sd.hw7.aspect;

import org.springframework.stereotype.Component;
import ru.itmo.iandolzhanskii.sd.hw7.Main;

import java.util.HashMap;
import java.util.Map;

@Component
public class Profiler {
    private String targetPackage = Main.class.getPackageName();
    private final Map<String, Long> callCounts = new HashMap<>();
    private final Map<String, Long> totalCallLatency = new HashMap<>();

    public String getTargetPackage() {
        return targetPackage;
    }

    public void setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
    }

    public void register(String methodName, long latencyNanos) {
        callCounts.compute(methodName, (name, prevCount) -> (prevCount == null) ? 1 : prevCount + 1);
        totalCallLatency.compute(methodName,
                (name, totalNanos) -> (totalNanos == null) ? latencyNanos : totalNanos + latencyNanos);
    }

    public void renderStatistics() {
        if (callCounts.isEmpty()) {
            System.out.println("No data collected");
            return;
        }
        int maxMethodNameLength = callCounts.keySet().stream().mapToInt(String::length).max().getAsInt();
        String format = "%-" + maxMethodNameLength + "s | %-18s | %-18s | %-18s%n";
        System.out.format(format, "Method name", "Calls count", "Total latency ns", "Avg latency ns");
        callCounts.keySet().stream().sorted().forEachOrdered(methodName -> {
            long callsCount = callCounts.get(methodName);
            long totalLatencyNs = totalCallLatency.get(methodName);
            long avgLatencyNs = totalLatencyNs / callsCount;
            System.out.format(format, methodName, callsCount, totalLatencyNs, avgLatencyNs);
        });
    }
}
