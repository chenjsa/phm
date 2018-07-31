package com.pms.rcm.notes.vo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="srs_notes")
public class SrsNotes  implements java.io.Serializable {

    private static final long serialVersionUID = 306993143365L; 
    public SrsNotes() { 
    } 
   
    @Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid") 
	@Column(name="id")
	private String id; 
	public String getId() {
		return this.id;
	} 
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="notes_code")
	private String notesCode; 
	public String getNotesCode() {
		return this.notesCode;
	} 
	public void setNotesCode(String notesCode) {
		this.notesCode = notesCode;
	}
	@Column(name="notes_type")
	private String notesType; 
	public String getNotesType() {
		return this.notesType;
	} 
	public void setNotesType(String notesType) {
		this.notesType = notesType;
	}
	@Column(name="notes_content")
	private String notesContent; 
	public String getNotesContent() {
		return this.notesContent;
	} 
	public void setNotesContent(String notesContent) {
		this.notesContent = notesContent;
	}
	@Column(name="notes_send_result")
	private String notesSendResult; 
	public String getNotesSendResult() {
		return notesSendResult;
	}
	public void setNotesSendResult(String notesSendResult) {
		this.notesSendResult = notesSendResult;
	}

	@Column(name="notes_is_read")
	private String notesIsRead; 
	public String getNotesIsRead() {
		return this.notesIsRead;
	} 
	public void setNotesIsRead(String notesIsRead) {
		this.notesIsRead = notesIsRead;
	}
	@Column(name="notes_receiver_id")
	private String notesReceiverId; 
	public String getNotesReceiverId() {
		return this.notesReceiverId;
	} 
	public void setNotesReceiverId(String notesReceiverId) {
		this.notesReceiverId = notesReceiverId;
	}
	@Column(name="notes_receiver_name")
	private String notesReceiverName; 
	public String getNotesReceiverName() {
		return this.notesReceiverName;
	} 
	public void setNotesReceiverName(String notesReceiverName) {
		this.notesReceiverName = notesReceiverName;
	}
	@Column(name="notes_sender_id")
	private String notesSenderId; 
	public String getNotesSenderId() {
		return this.notesSenderId;
	} 
	public void setNotesSenderId(String notesSenderId) {
		this.notesSenderId = notesSenderId;
	}
	@Column(name="notes_sender_name")
	private String notesSenderName; 
	public String getNotesSenderName() {
		return this.notesSenderName;
	} 
	public void setNotesSenderName(String notesSenderName) {
		this.notesSenderName = notesSenderName;
	}
	@Column(name="notes_gen_time")
	private String notesGenTime; 
	public String getNotesGenTime() {
		return this.notesGenTime;
	} 
	public void setNotesGenTime(String notesGenTime) {
		this.notesGenTime = notesGenTime;
	}
	@Column(name="notes_read_time")
	private String notesReadTime; 
	public String getNotesReadTime() {
		return this.notesReadTime;
	} 
	public void setNotesReadTime(String notesReadTime) {
		this.notesReadTime = notesReadTime;
	}

	@Column(name="plan_exe_id")
	private String planExeId;
	public String getPlanExeId() {
		return planExeId;
	}
	public void setPlanExeId(String planExeId) {
		this.planExeId = planExeId;
	}

	

  





}


