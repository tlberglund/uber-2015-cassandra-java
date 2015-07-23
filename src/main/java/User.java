import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import java.util.Date;
import java.util.UUID;

@Table(keyspace="killr_video", name="users")
public class User {

   @PartitionKey
   @Column(name="user_id")
   private UUID userId;

   @Column(name="created_date")
   private Date createdDate;

   private String email;

   @Column(name="first_name")
   private String firstName;

   @Column(name="last_name")
   private String lastName;


   public User() {
   }

   public User(Date createdDate, String email, String firstName, String lastName) {
      this.userId = UUID.randomUUID();
      this.createdDate = createdDate;
      this.email = email;
      this.firstName = firstName;
      this.lastName = lastName;
   }

   public UUID getUserId() {
      return userId;
   }

   public void setUserId(UUID userId) {
      this.userId = userId;
   }

   public Date getCreatedDate() {
      return createdDate;
   }

   public void setCreatedDate(Date createdDate) {
      this.createdDate = createdDate;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }


   @Override
   public String toString() {
      return "User{" +
              "email='" + email + '\'' +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              '}';
   }
}
