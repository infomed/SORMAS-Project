package de.symeda.sormas.api.caze;

import java.io.Serializable;
import java.util.Date;

import de.symeda.sormas.api.Disease;
import de.symeda.sormas.api.person.PersonReferenceDto;
import de.symeda.sormas.api.utils.DataHelper;

public class MapCase implements Serializable {

	private static final long serialVersionUID = -3021332968056368431L;

	public static final String I18N_PREFIX = "CaseData";

	private String uuid;
	private Date reportDate;
	private CaseClassification caseClassification;
	private Disease disease;
	private String healthFacilityUuid;
	private String personUuid;
	private PersonReferenceDto person;
	private Double reportLat;
	private Double reportLon;

	public MapCase(String uuid, Date reportDate, CaseClassification caseClassification, Disease disease,
			String healthFacilityUuid, String personUuid, Double reportLat, Double reportLon) {
		this.uuid = uuid;
		this.reportDate = reportDate;
		this.caseClassification = caseClassification;
		this.disease = disease;
		this.healthFacilityUuid = healthFacilityUuid;
		this.personUuid = personUuid;
		this.reportLat = reportLat;
		this.reportLon = reportLon;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public CaseClassification getCaseClassification() {
		return caseClassification;
	}

	public void setCaseClassification(CaseClassification caseClassification) {
		this.caseClassification = caseClassification;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public String getHealthFacilityUuid() {
		return healthFacilityUuid;
	}

	public void setHealthFacilityUuid(String healthFacilityUuid) {
		this.healthFacilityUuid = healthFacilityUuid;
	}

	public String getPersonUuid() {
		return personUuid;
	}

	public void setPersonUuid(String personUuid) {
		this.personUuid = personUuid;
	}

	public PersonReferenceDto getPerson() {
		return person;
	}

	public void setPerson(PersonReferenceDto person) {
		this.person = person;
	}

	public Double getReportLat() {
		return reportLat;
	}

	public void setReportLat(Double reportLat) {
		this.reportLat = reportLat;
	}

	public Double getReportLon() {
		return reportLon;
	}

	public void setReportLon(Double reportLon) {
		this.reportLon = reportLon;
	}	
	
	@Override
	public String toString() {
		return person.toString() + " (" + DataHelper.getShortUuid(uuid) + ")";
	}

}
