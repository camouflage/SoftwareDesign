package com.example.myticket.db;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.example.myticket.R;
import com.example.myticket.entities.Cinema;
import com.example.myticket.entities.Location;
import com.example.myticket.entities.Movie;
import com.example.myticket.entities.ProductDescription;
import com.example.myticket.entities.ScreeningRoom;
import com.example.myticket.entities.User;

import android.R.integer;
import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

public class MyApplication extends Application{
	private User _u;
	private Map<String, Integer> _photo_map;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
		String phone = sharedPreferences.getString("phone", " ");
		_u = DataBaseHelper.getInstance(getApplicationContext()).queryUser(phone);
		_photo_map = new HashMap<String, Integer>();
		_photo_map.put("photo1", new Integer(R.drawable.photo1));
		_photo_map.put("photo2", new Integer(R.drawable.photo2));
		_photo_map.put("photo3", new Integer(R.drawable.photo3));
		_photo_map.put("photo4", new Integer(R.drawable.photo4));
		_photo_map.put("photo5", new Integer(R.drawable.photo5));
		initData();
	}

	// setUser
	public void setUser(User user) {
		this._u = user;
	}

	// getUser
	public User getUser() {
		return _u;
	}

	// getPhotoMap()
	public Map<String, Integer> getPhotoMap() {
		return _photo_map;
	}

	// Initialization
	private void initData() {
		DataBaseHelper dbHelper = DataBaseHelper.getInstance(getApplicationContext());
		// Init cinima
		Cinema[] c = new Cinema[4];
		c[0] = new Cinema();
		c[0].setCinema_id("c1");
		c[0].setCinema_name("�๬��Ӱ��");
		c[0].setDist_code("440104 510180");

		c[1] = new Cinema();
		c[1].setCinema_id("c2");
		c[1].setCinema_name("���ѷ����Ӱ��");
		c[1].setDist_code("440106 510510");
		
		c[2] = new Cinema();
		c[2].setCinema_id("c3");
		c[2].setCinema_name("��������ӰԺ");
		c[2].setDist_code("440111 510440");
		
		c[3] = new Cinema();
		c[3].setCinema_id("c4");
		c[3].setCinema_name("�ж�����ӰԺ");
		c[3].setDist_code("440105 510220");

		for (int i = 0; i < c.length; i++) {
			dbHelper.addCinema(c[i]);
		}
		
//		ArrayList<Cinema> cs = dbHelper.queryAllCinema();
//		for (Cinema cinema : cs) {
//			Log.i("test", cinema.toString());
//		}


		// Init location
		dbHelper.addLocation(new Location("440106 510510", "�㶫ʡ�����������"));
		dbHelper.addLocation(new Location("440103 510145", "�㶫ʡ������������"));
		dbHelper.addLocation(new Location("440104 510180", "�㶫ʡ������Խ����"));
		dbHelper.addLocation(new Location("440105 510220", "�㶫ʡ�����к�����"));
		dbHelper.addLocation(new Location("440111 510440", "�㶫ʡ�����а�����"));
		dbHelper.addLocation(new Location("440112 510700", "�㶫ʡ�����л�����"));
		dbHelper.addLocation(new Location("440113 511400", "�㶫ʡ�����з�خ��"));
		dbHelper.addLocation(new Location("440114 510800", "�㶫ʡ�����л�����"));
		

		ScreeningRoom[] sr = new ScreeningRoom[5];
		sr[0] = new ScreeningRoom("s1", "A", 10, 8);
		sr[1] = new ScreeningRoom("s2", "B", 12, 10);
		sr[2] = new ScreeningRoom("s3", "C", 8, 7);
		sr[4] = new ScreeningRoom("s5", "E", 10, 8);
		sr[3] = new ScreeningRoom("s4", "D", 13, 9);
		// init ScreeningRoom
		for (int i = 0; i < sr.length; i++) {
			dbHelper.addScreeningRoom(sr[i]);
		}



		// init Movie
		Movie[] m = new Movie[5];
		m[0] = new Movie();
		m[1] = new Movie();
		m[2] = new Movie();
		m[3] = new Movie();
		m[4] = new Movie();

		m[0].setMovie_id("m[0]");
		m[0].setActors("�����ǣ����һԣ�����ݣ���ӽɺ�����󷢣������̣�����͢����v��");
		m[0].setDescription("���ܻԣ��������Σ���������һ��������޶�ȴ�����˴�ͽ��ܡ���Ȼ�Ѿ��Ļ�Ҫ�Ȼ��޶����������������ӻ������ڴ�ͽ֮�֡�������ʧȥ��������ʹ����Ҫץס��ͽ���ͽ���ˡ���������Ϊ������һ��Ҫ������ܰ���Ļ����ϰ塣���ǣ���ƾһ���˵�����Ҫ�Ը�������׼������ս�թ֮ͽ����ʵ�Ǹ���С����������ս��˼��������������˵���Ѿ����ݵ�ǰ���񴦸��������ı����һ��Σ��ٶȸ�����Э�������ư������Ŷ԰�����������飬���Ƿ�����˭������Ԥ�ϵ����¡����ı�Ķ��Ӿ�Ȼ��Ļ����ϰ�����ʮ�ֽ��ܵ���ϵ��֮��������֮���ٲ��˵ı���һ�����Ƕ��¡�");
		
		m[0].setDirector("½���ࡢ������");
		m[0].setDuration(110);
		m[0].setLanguages("������ͨ��������");
		m[0].setMovie_name("��ս2");
		m[0].setPhoto("photo1");
		m[0].setPoint(new Float(7.2));
		m[0].setSale_account(79961);
		m[0].setTag("���顢����������");

		m[1].setMovie_id("m[1]");
		m[1].setActors("����ɽ��˹��˹�ӵ£�����ء��ޱȣ�����˹�и����߶��ȣ����Ѷ����ܿ�ѷ������������");
		m[1].setDescription("19����80�����̩ɽ������ɽ��˹��˹�ӵ��Σ��Ѿ��뿪�˸չ�����ʮ���ˣ������İ����䡤���أ�����ء��ޱ��Σ���ά������Ů��ʱ����Ӣ���׶��������ƽ���ĳ��������̩ɽ��˵����������ڣ������е���Ϣ�����ſ����������ס�ں�լ�ȴ�Ҳ����ҵĸо���һ�Σ�����Ӣ�����ó�״�ʹ��������»ص��չ����������ǲʵ������һ�α���ʱ���ﰺ����ķ������˹�и����߶����Σ�����ı��̩ɽ�����Լ����������Ƕ�������Σ�գ���������̩ɽ�����£�����ʼ���·����߱ڣ����Ȱ��˺����ѡ�");
		m[1].setDirector("������Ҷ��");
		m[1].setDuration(109);
		m[1].setLanguages("Ӣ��");
		m[1].setMovie_name("̩ɽ��������ս����");
		m[1].setPhoto("photo2");
		m[1].setPoint(new Float(8.3));
		m[1].setSale_account(52764);
		m[1].setTag("������ð��");

		m[2].setMovie_id("m[2]");
		m[2].setActors("���ෲ������ƣ������ѣ����ߣ����Σ�������");
		m[2].setDescription("������ᰲ�������Ͻ���������Σ��ڸ���ͬѧ������ෲ�Σ�������׷�������������˶Է����������మ�����ദ�ѣ���С������Ĳ����Լ��Ը�����쿪ʼ��Ϊ����֮������⣬ì�ܵĲ��Ϸ������ջ����ø���������Ѻ�����ߵ��˷��־��ء���������Ͻ���Ϊ��һ����ҵ�гɵ�ְ��Ůǿ�ˣ���ƽ�������������ٴγ��ֶ����������������˼��������ҵĸ����ʹ�����־�������һ�𣬲�����ʱ��֪���˴����⼸���ж�û�з��£��밮�����¹�ȥ���ݡ�");
		m[2].setDirector("������");
		m[2].setDuration(98);
		m[2].setLanguages("������ͨ��");
		m[2].setMovie_name("���ഺ��ԭ���㻹������");
		m[2].setPhoto("photo3");
		m[2].setPoint(new Float(4.0));
		m[2].setSale_account(23238);
		m[2].setTag("���顢�ഺ������");
		
		m[3].setMovie_id("m[3]");
		m[3].setActors("���������μѣ������ģ�������");
		m[3].setDescription("½���������� �Σ����ϴ�ѧʱ�ط��׶�԰ͬѧ�������μ� �Σ�����Ȼ�˴��Ĵ�øУ�������½�������صġ�����ϰ�֢����ֻ���۰ͰͿ����Լ���Ů�����������̸���������Դ�֮��ܶ��꣬½��ֻ�������ѵ����尮��������Ҳ������ʼ��һ�Ρ����鲻�ʡ����˲��ҡ��ĳ��ܡ�");
		m[3].setDirector("����");
		m[3].setDuration(107);
		m[3].setLanguages("������ͨ��");
		m[3].setMovie_name("½��֪����");
		m[3].setPhoto("photo4");
		m[3].setPoint(new Float(5.1));
		m[3].setSale_account(36728);
		m[3].setTag("ϲ�� ������");
		
		m[4].setMovie_id("m[4]");
		m[4].setActors("�����أ������䣬��κ�ޣ���ʿ��");
		m[4].setDescription("�����������궼�Ǻ���һ���޴���㣬������ʱ��Ӻ��Ĵ˰���������·;�У���ʱ��������ʱ�ֿ�������ʱ��ȥ�����ı˰���֮����һ����˯��С�㣬�ȴ��������ٴγ���������ó���Զ�������������������Ϣ��ʮ�����������죬��ס�ڡ���֮Χ¥�����һ�����д���Ů������һ�����ൽ�˼�Ѳ�񣬱����е�һ������ס��һ�������к���Ϊ�������������ȥ��Ϊ�˱�����Ϊ���������к��������Ҫ���Լ�����������������������谭�����������к�����ꡪ��һ��Ĵָ��ô���С�㣬�ɳ�Ϊһ���Ⱦ����޴���㲢�ع�󺣡�");
		m[4].setDirector("�������Ŵ�");
		m[4].setDuration(115);
		m[4].setLanguages("������ͨ��");
		m[4].setMovie_name("���㺣��");
		m[4].setPhoto("photo5");
		m[4].setPoint(new Float(6.2));
		m[4].setSale_account(62953);
		m[4].setTag("���������");
		for (int i = 0; i < m.length; i++) {
			dbHelper.addMovie(m[i]);
		}
		Random random = new Random();
		for (int i = 1; i <= 80; i++) {
			int sr_index = random.nextInt(sr.length);
			ProductDescription p = new ProductDescription("p"+Integer.toString(i), 
					m[random.nextInt(m.length)].getMovie_id(), 
					c[random.nextInt(c.length)].getCinema_id(),
					sr[sr_index].getScreening_room_id());
			p.setPrice((float)(random.nextInt(41) + 60));
			p.setType("3D");
			StringBuilder sb = new StringBuilder("1");
			for (int j = 1; j < sr[sr_index].getCol() * sr[sr_index].getRow(); j++) {
				sb.append(",1");
			}
			String s = sb.toString();
			p.setSeat_availible(s);
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.HOUR_OF_DAY, random.nextInt(11) - 5);
			calendar.add(Calendar.MINUTE, random.nextInt(60));
			calendar.add(Calendar.DAY_OF_MONTH, random.nextInt(3));
			p.setStartTime(calendar.getTime()); 
			dbHelper.addProductDescription(p);
		}


	}
}
