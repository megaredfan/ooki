package cn.parabola.ooki.core.mapper;

import cn.parabola.ooki.core.model.Account;

public interface AccountMapper {

	Account getAccount(String account,int platformType);
	int addAccount(Account account);
	int delAccount(int id);
}
