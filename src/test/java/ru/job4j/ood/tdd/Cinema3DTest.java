package ru.job4j.ood.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.Calendar;
import java.util.List;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
public class Cinema3DTest {
    @Test
    public void whenBuyThenGetTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    public void whenAddSessionThenItExistsBetweenAllSessions() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(data -> true);
        assertThat(sessions).contains(session);
    }

    @Test
    public void whenBuyOnInvalidRowThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, -1, 1, date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenDeleteSessionThenItDoesNotExist() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        Session session2 = new Session3D();
        cinema.add(session2);
        cinema.delete(session);
        List<Session> sessions = cinema.find(data -> true);
        assertThat(sessions).doesNotContain(session2);
    }

    @Test
    public void whenBuyTicketOnOccupiedPlaceThenGetException() {
        Account account1 = new AccountCinema();
        Account account2 = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        cinema.buy(account1, 1, 1, date);
        assertThatThrownBy(() -> cinema.buy(account2, 1, 1, date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Место занято");
    }

    @Test
    public void whenBuyTicketOnInvalidDateThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar invalidDate = Calendar.getInstance();
        invalidDate.set(2024, Calendar.FEBRUARY, 30);
        assertThatThrownBy(() -> cinema.buy(account, 1, 1, invalidDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Неправильная дата");
    }

    @Test
    public void whenAddDuplicateSessionThenGetException() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        assertThatThrownBy(() -> cinema.add(session))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Сеанс существует");
    }
}