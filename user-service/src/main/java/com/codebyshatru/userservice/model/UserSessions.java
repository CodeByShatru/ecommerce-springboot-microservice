package com.codebyshatru.userservice.model;

import java.time.LocalDate;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_sessions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSessions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "session_id")
	private Long sessionId;

	@Column(name = "secret_token")
	private String secretToken;

	@Column(name = "secret_token_time")
	private LocalDate secretTokenTime;

	@Column(name = "token_deleted")
	private Boolean tokenDeleted;

	@Column(name = "ip_address")
	private String ipAddress;

	@Column(name = "device_details")
	private String deviceDetails;

	@Column(name = "session_count")
	private Integer sessionCount;

	@Column(name = "created_dt")
	private LocalDate createdDt;

	@Column(name = "updated_dt")
	private LocalDate updatedDt;

}
