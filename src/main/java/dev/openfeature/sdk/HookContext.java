package dev.openfeature.sdk;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.With;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A data class to hold immutable context that {@link Hook} instances use.
 *
 * @param <T> the type for the flag being evaluated
 */
@Value @Builder @With
public class HookContext<T> {
    @NonNull String flagKey;
    @NonNull FlagValueType type;
    @NonNull Supplier<T> defaultValue;
    @NonNull EvaluationContext ctx;
    Metadata clientMetadata;
    Metadata providerMetadata;

    /**
     * Builds a {@link HookContext} instances from request data.
     * @param key feature flag key
     * @param type flag value type
     * @param clientMetadata info on which client is calling
     * @param providerMetadata info on the provider
     * @param ctx Evaluation Context for the request
     * @param defaultValue Fallback value
     * @param <T> type that the flag is evaluating against
     * @return resulting context for hook
     */
    public static <T> HookContext<T> from(String key, FlagValueType type, Metadata clientMetadata,
                                          Metadata providerMetadata, EvaluationContext ctx, Supplier<T> defaultValue) {
        return HookContext.<T>builder()
                .flagKey(key)
                .type(type)
                .clientMetadata(clientMetadata)
                .providerMetadata(providerMetadata)
                .ctx(ctx)
                .defaultValue(defaultValue)
                .build();
    }
}
