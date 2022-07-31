package com.glauber.nfeuploadservice.api.exceptionhandler.problem;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class ApiProblemDetail {
	
		private Integer status;
		private String title;
		private String detail;
		private OffsetDateTime timestamp;
		private String userMessage;
		private List<Field> fields;		
		
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDetail() {
			return detail;
		}
		public void setDetail(String detail) {
			this.detail = detail;
		}
		public OffsetDateTime getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(OffsetDateTime timestamp) {
			this.timestamp = timestamp;
		}
		public String getUserMessage() {
			return userMessage;
		}
		public void setUserMessage(String userMessage) {
			this.userMessage = userMessage;
		}
		public List<Field> getFields() {
			return fields;
		}
		public void setFields(List<Field> fields) {
			this.fields = fields;
		}


		public static class Field {
			private String name;
			private String userMessage;
			
			public Field(String name, String userMessage) {
				this.name = name;
				this.userMessage = userMessage;
			}
			
			public String getName() {
				return name;
			}
			public String getUserMessage() {
				return userMessage;
			}
			
		}
}
