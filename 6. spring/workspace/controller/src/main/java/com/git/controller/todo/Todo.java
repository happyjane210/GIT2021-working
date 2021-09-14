package com.git.controller.todo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor // 朔持失切
@AllArgsConstructor // 穿端 持失切
public class Todo {
	private long id;
	private String memo;
	private long createdTime;

}
