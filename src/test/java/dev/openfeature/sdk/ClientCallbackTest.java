package dev.openfeature.sdk;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientCallbackTest {

    @Test
    void clientProviderTest() {
        OpenFeatureAPI api = OpenFeatureAPI.getInstance();
        api.setProvider(new ErrorProvider());
        Client client = api.getClient();

        AtomicBoolean callbackCalled = new AtomicBoolean(false);
        boolean val = client.getBooleanValue("myFlag",  () -> {
            // simulate expensive call
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // ignore
            }
            callbackCalled.set(true);
            return true;
        });
        assertTrue(callbackCalled.get());
    }

    class ErrorProvider implements FeatureProvider {

        @Override
        public Metadata getMetadata() {
            return null;
        }

        @Override
        public ProviderEvaluation<Boolean> getBooleanEvaluation(String key, Boolean defaultValue, EvaluationContext ctx) {
            return null;
        }

        @Override
        public ProviderEvaluation<String> getStringEvaluation(String key, String defaultValue, EvaluationContext ctx) {
            return null;
        }

        @Override
        public ProviderEvaluation<Integer> getIntegerEvaluation(String key, Integer defaultValue, EvaluationContext ctx) {
            return null;
        }

        @Override
        public ProviderEvaluation<Double> getDoubleEvaluation(String key, Double defaultValue, EvaluationContext ctx) {
            return null;
        }

        @Override
        public ProviderEvaluation<Value> getObjectEvaluation(String key, Value defaultValue, EvaluationContext ctx) {
            return null;
        }
    }
}
