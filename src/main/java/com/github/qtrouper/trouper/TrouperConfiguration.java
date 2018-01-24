package com.github.qtrouper.trouper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author koushik
 */
@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrouperConfiguration {

    private List<QueueConfiguration> queues = new ArrayList<>();

    @JsonIgnore
    public QueueConfiguration getConsumerConfiguration(String queueName) {
        if (queues == null || queues.isEmpty())
            return QueueConfiguration.getDefaultConfiguration(queueName);

        final Optional<QueueConfiguration> first = queues.stream().filter(each -> each.getQueueName().equalsIgnoreCase(queueName)).findFirst();

        return first.orElseGet(() -> QueueConfiguration.getDefaultConfiguration(queueName));
    }

}
