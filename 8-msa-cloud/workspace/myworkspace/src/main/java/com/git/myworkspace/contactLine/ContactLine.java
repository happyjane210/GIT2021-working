package com.git.myworkspace.contactLine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ContactLine {
	private long id;
	private String name;
	private String phone;
	private String email;
	private Long createdTime;
}
