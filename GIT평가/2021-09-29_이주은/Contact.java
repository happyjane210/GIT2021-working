package com.git.myworkspace.contact;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Contact {
	// 필드의 id를 테이블의 Primary key로.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 생성전략 IDENTITY
	private long id;
	private String name;
	private String phone;
	private String email;
	private String memo;
	private long createdTime;
}
