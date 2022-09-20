package site.metacoding.red.domain.loves;

import java.util.List;

public interface LovesDao {
	public void insert(Loves loves); //리턴 타입이 없어도 mybatis 에서 주소설정이 되어있기때문에 넣어줌 keyProperty="id"
	public List<Loves> findAll();
	public Loves findById(Integer id);
	public void update(Loves loves);
	public void deleteById(Integer lovesId);
}
