package logging.logger.config;

import java.util.ArrayDeque;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import logging.logger.domain.enums.Logger;
import logging.logger.domain.model.DataModel;
import lombok.Getter;

@Component
public class Mapper {
    private final Map<Logger, Node> map;

    @Getter
    private static class Node {
        private final ArrayDeque<Logger> q;
        private final Map<String, Logger> m;

        private Node() {
            q = new ArrayDeque<>();
            m = new HashMap<>();
        }
    }

    // Invokes the constructor exactly once since the class is Singleton
    public Mapper() {
        this.map = new EnumMap<>(Logger.class);
        map.put(Logger.INFO, new Node());
        map.put(Logger.WARN, new Node());
        map.put(Logger.ERROR, new Node());
    }

    public void addToMap(DataModel dm) {
        if(map.get(dm.getLogger()).getQ().size() > 3)
            erase(dm);
        update(dm);
    }

    public void show() {
        showMap();
    }

    private void erase(DataModel dm) {
        String s = dm.getUuid();
        map.get(dm.getLogger()).getM().remove(s);
        map.get(dm.getLogger()).getQ().pollFirst();
    }

    private void update(DataModel dm) {
        map.get(dm.getLogger()).getM().put(dm.getUuid(), dm.getLogger());
        map.get(dm.getLogger()).getQ().addLast(dm.getLogger());
    }

    private void showMap() {
        for (Map.Entry<Logger, Node> en : map.entrySet()) {
            System.out.println("Log Type : "+en.getKey());
            for(Map.Entry<String, Logger> et : en.getValue().getM().entrySet()) {
                System.out.println(et.getKey()+" -> "+et.getValue());
            }
        }
    }
}
