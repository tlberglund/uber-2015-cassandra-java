import com.datastax.driver.core.*;
import com.datastax.driver.core.policies.DowngradingConsistencyRetryPolicy;
import com.datastax.driver.core.policies.LoggingRetryPolicy;import com.datastax.driver.core.policies.TokenAwarePolicy;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;

import static com.datastax.driver.core.querybuilder.QueryBuilder.*;


import java.util.Date;
import java.util.UUID;


public class Cassandra {
   private static String clusterAddress = "52.27.37.210";
   Cluster cluster;

   public Session connect() {
      cluster = Cluster
              .builder()
              .addContactPoint(clusterAddress)
              .withRetryPolicy(new LoggingRetryPolicy(DowngradingConsistencyRetryPolicy.INSTANCE))
              .build();
      return cluster.connect("killr_video");
   }

   public void hardCodedInsert(Session session) {
      session.execute("INSERT INTO users ( user_id, created_date, email, first_name, last_name) VALUES (14c532ac-f5ae-479a-9d0a-36604732e01d, '2015-07-22 00:00:00', 'tim.berglund@datastax.com', 'Tim', 'Berglund')");
   }

   public void preparedInsert(Session session,
                              String email,
                              String firstName,
                              String lastName) {
      PreparedStatement smt = session.prepare("INSERT INTO users (user_id, created_date, email, first_name, last_name) VALUES (?, ?, ?, ?, ?)");

      BoundStatement bound = new BoundStatement(smt);
      BoundStatement bs = bound.bind(UUID.randomUUID(), new Date(),email, firstName, lastName);
      session.execute(bs);
   }


   public void printUsers(Session session) {
      Statement allUsers = QueryBuilder.select().all().from("killr_video", "users");
      ResultSet rs = session.execute(allUsers);
      for(Row row : rs) {
         System.out.printf("%s %s %s\n",
                           row.getString("email"),
                           row.getString("first_name"),
                           row.getString("last_name"));
      }
   }


   public void addUser(Session session, User user) {
      MappingManager mappingManager;
      mappingManager = new MappingManager(session);
      Mapper<User> mapper = mappingManager.mapper(User.class);
      mapper.save(user);
   }

   public User getUser(Session session, UUID userId) {
      MappingManager mappingManager;
      mappingManager = new MappingManager(session);
      UserAccessor accessor = mappingManager.createAccessor(UserAccessor.class);
      return accessor.get(userId);
   }


   public static void main(String args[]) {
      Cassandra c = new Cassandra();
      Session session = c.connect();
      //c.preparedInsert(session, "fird@birfle.com", "Howard", "Lewis Ship");

      //User user = new User(new Date(), "email@address.com", "Stanford", "Guillory");
      //c.addUser(session, user);

      User tim = c.getUser(session, UUID.fromString("14c532ac-f5ae-479a-9d0a-36604732e01d"));
      System.out.println(tim);

//      c.printUsers(session);



   }
}
