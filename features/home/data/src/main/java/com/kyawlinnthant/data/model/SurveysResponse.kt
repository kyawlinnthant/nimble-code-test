package com.kyawlinnthant.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*{
    "data": [
    {
        "id": "d5de6a8f8f5f1cfe51bc",
        "type": "survey_simple",
        "attributes": {
        "title": "Scarlett Bangkok",
        "description": "We'd love ot hear from you!",
        "thank_email_above_threshold": "<span style=\"font-family:arial,helvetica,sans-serif\"><span style=\"font-size:14px\">Dear {name},<br /><br />Thank you for visiting Scarlett Wine Bar &amp; Restaurant at Pullman Bangkok Hotel G &nbsp;and for taking the time to complete our guest feedback survey!<br /><br />Your feedback is very important to us and each survey is read individually by the management and owners shortly after it is sent. We discuss comments and suggestions at our daily meetings and use them to constantly improve our services.<br /><br />We would very much appreciate it if you could take a few more moments and review us on TripAdvisor regarding your recent visit. By <a href=\"https://www.tripadvisor.com/Restaurant_Review-g293916-d2629404-Reviews-Scarlett_Wine_Bar_Restaurant-Bangkok.html\">clicking here</a> you will be directed to our page.&nbsp;<br /><br />Thank you once again and we look forward to seeing you soon!<br /><br />The Team at Scarlett Wine Bar &amp; Restaurant&nbsp;</span></span><span style=\"font-family:arial,helvetica,sans-serif; font-size:14px\">Pullman Bangkok Hotel G</span>",
        "thank_email_below_threshold": "<span style=\"font-size:14px\"><span style=\"font-family:arial,helvetica,sans-serif\">Dear {name},<br /><br />Thank you for visiting&nbsp;</span></span><span style=\"font-family:arial,helvetica,sans-serif; font-size:14px\">Uno Mas at Centara Central World&nbsp;</span><span style=\"font-size:14px\"><span style=\"font-family:arial,helvetica,sans-serif\">&nbsp;and for taking the time to complete our customer&nbsp;feedback survey.</span></span><br /><br /><span style=\"font-family:arial,helvetica,sans-serif; font-size:14px\">The Team at&nbsp;</span><span style=\"font-family:arial,helvetica,sans-serif\"><span style=\"font-size:14px\">Scarlett Wine Bar &amp; Restaurant&nbsp;</span></span><span style=\"font-family:arial,helvetica,sans-serif; font-size:14px\">Pullman Bangkok Hotel G</span>",
        "is_active": true,
        "cover_image_url": "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_",
        "created_at": "2017-01-23T07:48:12.991Z",
        "active_at": "2015-10-08T07:04:00.000Z",
        "inactive_at": null,
        "survey_type": "Restaurant"
    }
    }, ..
    ]
}*/

@Serializable
data class SurveysResponse(
    val data: List<Surveys>
)

@Serializable
data class Surveys(
    val id: String,
    val type: String,

    )

@Serializable
data class SurveyAttribute(
    val title: String,
    val description: String,
    @SerialName("thank_email_above_threshold")
    val aboveThreshold: String,
    @SerialName("thank_email_below_threshold")
    val belowThreshold: String,
    @SerialName("is_active")
    val isActive: Boolean,
    @SerialName("cover_image_url")
    val imageUrl: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("active_at")
    val activeAt: String?,
    @SerialName("survey_type")
    val surveyType: String
)