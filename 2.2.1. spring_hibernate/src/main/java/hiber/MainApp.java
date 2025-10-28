package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("VAZ", 2101);
      Car car2 = new Car("GAZ", 24);
      Car car3 = new Car("NIVA", 2121);
      Car car4 = new Car("UAZ", 469);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      userService.addUser(user1);
      userService.addUser(user2);
      userService.addUser(user3);
      userService.addUser(user4);

      List<User> users = userService.getListUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car: = " + user.getCar().getModel() + " " + user.getCar().getSeries());
         System.out.println();
      }

      User foundUser = userService.getUserCar("VAZ", 2101);
      System.out.println("Поиск юзера по автомобилю ВАЗ 2101:");
      System.out.println("Id = " + foundUser.getId());
      System.out.println("First Name = " + foundUser.getFirstName());
      System.out.println("Last Name = " + foundUser.getLastName());
      System.out.println("Email = " + foundUser.getEmail());

      context.close();
   }
}
