package com.demo.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Card {

	@Id
	private int cid;

	private String cardId;

	public int getCid() {
		return this.cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCardId() {
		return this.cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

}