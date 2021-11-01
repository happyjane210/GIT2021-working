package com.example.commerce.order;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Formula;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//엔터티 모델
@Entity
// 집합 루트 객체(Aggregation Root Object)
// 하위 객체들을 포함하고 있는 객체
// 도메인객체: 비즈니스에서 주요한 객체
public class CommerceOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private long id; // 자동증가값
	
	private String name;	// 주문자명
	private String address;	// 주소
	private String date;	// 주문일자
	
	private int totalAmount;	// 총 주문금액
	
	// 주문목록조회에 띄울 상세정보 요약 (사진, 제품이름, ..외 건, 판매처) 등
	
	// 하위 레코드 개수  (주문 한건에 함께 주문한 제품 개수)
	@Formula("(SELECT COUNT(1) FROM commerce_order_detail d WHERE d.commerce_order_id = id)")
	private int detailCnt;

	// 하위 레코드 중 첫번째 레코드의 정보										LIMIT 1	: id 순으로 맨 위에 있는 제품
	@Formula("(SELECT d.product_name FROM commerce_order_detail d WHERE d.commerce_order_id = id LIMIT 1)")
	private String firstProductName;	
	
	
	
	// 주문 제품 정보 목록
	// Entity Relationship 어노테이션을 넣으면 같이 조회됨
	// 기본적으로 FetchType.Lazy
	// - 코드 돌릴때 가져오지 않고, 실제로 조회할때, 실제로 데이터를 쓸려고 할때 조회해옴
	@OneToMany
	@JoinColumn(name="commerceOrderId")
	private List<CommerceOrderDetail> details;
	
	private String status;	// 상태
	private long createdTime;	// 생성시간
}