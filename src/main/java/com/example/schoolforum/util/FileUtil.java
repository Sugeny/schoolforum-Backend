package com.example.schoolforum.util;

import java.nio.file.Path;
import java.util.Arrays;

public final class FileUtil {

    private FileUtil() {
    }

    public static long parseSize(String size) {
        if (size == null || size.isBlank()) {
            return 0;
        }
        size = size.toUpperCase().trim();
        if (size.endsWith("GB")) {
            return Long.parseLong(size.replace("GB", "").trim()) * 1024L * 1024L * 1024L;
        } else if (size.endsWith("MB")) {
            return Long.parseLong(size.replace("MB", "").trim()) * 1024L * 1024L;
        } else if (size.endsWith("KB")) {
            return Long.parseLong(size.replace("KB", "").trim()) * 1024L;
        }
        return Long.parseLong(size.trim());
    }

    public static boolean isAllowedContentType(String contentType, String allowedTypes) {
        if (contentType == null || allowedTypes == null || allowedTypes.isBlank()) {
            return false;
        }
        return Arrays.stream(allowedTypes.split(","))
                .map(String::trim)
                .anyMatch(contentType::equals);
    }

    public static String imageExtensionForContentType(String contentType) {
        return switch (contentType) {
            case "image/jpeg" -> ".jpg";
            case "image/png" -> ".png";
            case "image/gif" -> ".gif";
            case "image/webp" -> ".webp";
            default -> "";
        };
    }

    public static boolean isSafeFilename(String filename) {
        return filename != null
                && !filename.isBlank()
                && !filename.contains("/")
                && !filename.contains("\\")
                && !".".equals(filename)
                && !"..".equals(filename);
    }

    public static Path safeResolve(Path basePath, String filename) {
        Path normalizedBasePath = basePath.toAbsolutePath().normalize();
        Path resolvedPath = normalizedBasePath.resolve(filename).normalize();
        if (!resolvedPath.startsWith(normalizedBasePath)) {
            throw new IllegalArgumentException("非法的文件路径");
        }
        return resolvedPath;
    }
}
