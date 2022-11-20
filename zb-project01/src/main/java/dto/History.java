package dto;

import lombok.*;

@Getter
@Setter
public class History {
	private long id; 
	private Double lat; // x좌표
	private Double lnt; // y좌표
	private String historyDate; // 조회일자
}
