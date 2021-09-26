package com.git.myworkspace.contact;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
	private long id;
	private String name;
	private String phone;
	private String email;
	private String memo;
	private long createdTime;
}
