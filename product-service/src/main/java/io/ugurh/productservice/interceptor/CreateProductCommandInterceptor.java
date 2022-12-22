package io.ugurh.productservice.interceptor;

import io.ugurh.productservice.core.command.CreateProductCommand;
import io.ugurh.productservice.core.data.entity.ProductLookupEntity;
import io.ugurh.productservice.core.data.entity.ProductLookupEntityRepository;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.BiFunction;

@Component
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateProductCommandInterceptor.class);

    private final ProductLookupEntityRepository repository;

    public CreateProductCommandInterceptor(ProductLookupEntityRepository repository) {
        this.repository = repository;
    }

    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> list) {
        return (index, command) -> {
            LOGGER.info("Intercepted Command: {}", command.getPayloadType());

            if (CreateProductCommand.class.equals(command.getPayloadType())) {
                CreateProductCommand createdProductCommand = (CreateProductCommand) command.getPayload();

                validation(createdProductCommand);
            }

            return command;
        };
    }

    private void validation(CreateProductCommand createdProductCommand) {
        ProductLookupEntity productLookupEntity = repository.findByProductIdOrTitle(createdProductCommand.getProductId(), createdProductCommand.getTitle());

        if (productLookupEntity != null) {
            String msg = String.format("Product with productId %s or title %s already exist",
                    createdProductCommand.getProductId(), createdProductCommand.getTitle());
            throw new IllegalStateException(msg);
        }
    }

}
