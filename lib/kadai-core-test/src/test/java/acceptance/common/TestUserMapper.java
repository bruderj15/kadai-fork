package acceptance.common;

import static io.kadai.common.internal.util.SqlProviderUtil.CLOSING_SCRIPT_TAG;
import static io.kadai.common.internal.util.SqlProviderUtil.DB2_WITH_UR;
import static io.kadai.common.internal.util.SqlProviderUtil.OPENING_SCRIPT_TAG;

import java.util.Objects;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

interface TestUserMapper {

  String USER_INFO_COLUMNS = "USER_ID, FIRST_NAME, LASTNAME, LONG_NAME";
  String USER_INFO_VALUES = "#{id}, #{firstName}, #{lastName}, #{longName}";

  @Select(
      OPENING_SCRIPT_TAG
          + "SELECT "
          + USER_INFO_COLUMNS
          + " FROM USER_INFO "
          + " WHERE USER_ID = #{id} "
          + DB2_WITH_UR
          + CLOSING_SCRIPT_TAG)
  @Result(property = "id", column = "USER_ID")
  @Result(property = "firstName", column = "FIRST_NAME")
  @Result(property = "lastName", column = "LASTNAME")
  @Result(property = "longName", column = "LONG_NAME")
  TestUser findById(String id);

  @Insert("INSERT INTO USER_INFO ( " + USER_INFO_COLUMNS + ") VALUES(" + USER_INFO_VALUES + ")")
  void insert(TestUser user);

  class TestUser {
    private String id;
    private String firstName;
    private String lastName;
    private String longName;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public TestUser(String id, String firstName, String lastName, String longName) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.longName = longName;
    }

    @Override
    public String toString() {
      return "TestUser [id="
          + id
          + ", firstName="
          + firstName
          + ", lastName="
          + lastName
          + ", longName="
          + longName
          + "]";
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      TestUser other = (TestUser) obj;
      return Objects.equals(id, other.id)
          && Objects.equals(firstName, other.firstName)
          && Objects.equals(lastName, other.lastName)
          && Objects.equals(longName, other.longName);
    }

    @Override
    public int hashCode() {
      return Objects.hash(id, firstName, lastName, longName);
    }
  }
}
