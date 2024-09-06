package se331.lab.dao;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import se331.lab.entity.Event;

import java.util.ArrayList;
import java.util.List;

public class EventDaolmpl {
    @Repository
    public class EventDaoImpl implements EventDao {
        List<Event> eventList;

        @PostConstruct
        public void init() {
            eventList = new ArrayList<>();
            eventList.add(Event.builder()
                    .id(123L)
                    .category("animal welfare")
                    .title("Cat Adoption Day")
                    .description("Find your new feline friend at this event.")
                    .location("Meow Town")
                    .date("January 28, 2022")
                    .time("12:00")
                    .petAllowed(true)
                    .organizer("Kat Laydee")
                    .build());
        }

        @Override
        public Integer getEventSize() {
            return eventList.size();
        }

        @Override
        public List<Event> getEvents(Integer pageSize, Integer page) {
            pageSize = pageSize == null ? eventList.size() : pageSize;
            page = page == null ? 1 : page;
            Integer firstIndex = (page - 1) * pageSize;
            List<Event> output = new ArrayList<>();
            for (int i = firstIndex; i < firstIndex + pageSize; i++) {
                output.add(eventList.get(i));
            }
            return output;
        }

        @Override
        public Event getEvent(Long id) {
            Event output = null;
            for (Event event : eventList) {
                if (event.getId().equals(id)) {
                    output = event;
                    break;
                }
            }
            return output;
        }
    }
}
