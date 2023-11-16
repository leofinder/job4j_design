package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0, changed = 0, deleted;
        Map<Integer, User> map = new HashMap<>();
        for (User user: previous) {
            map.put(user.getId(), user);
        }
        for (User user: current) {
            User el = map.get(user.getId());
            if (el == null) {
                added++;
            } else {
                if (!el.equals(user)) {
                    changed++;
                }
                map.remove(user.getId());
            }
        }
        deleted = map.size();
        return new Info(added, changed, deleted);
    }
}
