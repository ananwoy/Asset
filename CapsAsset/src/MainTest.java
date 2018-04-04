import java.util.List;

import com.cg.Dao.AdminDao;
import com.cg.Dao.ManagerDao;
import com.cg.bean.Asset;
import com.cg.bean.Status;

public class MainTest {
public static void main(String[] args) {
	AdminDao md=new AdminDao();
	List<Asset> li=md.generateReport("Available");
	for (Asset st : li) {
		System.out.println(st.getAssetId());
	}
}
}
