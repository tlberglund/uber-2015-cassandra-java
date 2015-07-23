import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;

import java.util.UUID;

@Accessor
public interface UserAccessor {

   @Query("SELECT * FROM killr_video.users WHERE user_id = :userId")
   User get(@Param("userId") UUID userId);

}
