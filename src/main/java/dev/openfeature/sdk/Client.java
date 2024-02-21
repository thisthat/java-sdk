package dev.openfeature.sdk;

import java.util.List;
import java.util.function.Supplier;

/**
 * Interface used to resolve flags of varying types.
 */
public interface Client extends Features, EventBus<Client> {
    Boolean getBooleanValue(String key, Supplier<Boolean> defaultValue);

    FlagEvaluationDetails<Boolean> getBooleanDetails(String key, Supplier<Boolean> defaultValue);

    FlagEvaluationDetails<Boolean> getBooleanDetails(String key, Supplier<Boolean> defaultValue, EvaluationContext ctx,
                                                     FlagEvaluationOptions options);

    Metadata getMetadata();

    /**
     * Return an optional client-level evaluation context.
     * @return {@link EvaluationContext}
     */
    EvaluationContext getEvaluationContext();

    /**
     * Set the client-level evaluation context.
     * @param ctx Client level context.
     */
    void setEvaluationContext(EvaluationContext ctx);

    /**
     * Adds hooks for evaluation.
     * Hooks are run in the order they're added in the before stage. They are run in reverse order for all other stages.
     *
     * @param hooks The hook to add.
     */
    void addHooks(Hook... hooks);

    /**
     * Fetch the hooks associated to this client.
     * @return A list of {@link Hook}s.
     */
    List<Hook> getHooks();
}
