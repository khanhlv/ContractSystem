CREATE TABLE [CATEGORY] (
	[CATEGORY_ID] int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	[CATEGORY_NAME] nvarchar(250) NULL ,
	[CATEGORY_DESCRIPTION] nvarchar(550) NULL ,
	[CREATED_DATE] datetime NULL DEFAULT (getdate()) ,
	[CREATED_USER_ID] int NULL ,
	[UPDATED_DATE] datetime NULL ,
	[UPDATED_USER_ID] int NULL ,
	[STATUS] tinyint NOT NULL DEFAULT ((1)) 
)
GO
CREATE TABLE [TEMPLATE] (
	[TEMPLATE_ID] int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	[TEMPLATE_NAME] nvarchar(250) NULL ,
	[TEMPLATE_DESCRIPTION] nvarchar(550) NULL,
	[TEMPLATE_FILE_ID] int,
	[CREATED_DATE] datetime NULL DEFAULT (getdate()) ,
	[CREATED_USER_ID] int NULL ,
	[UPDATED_DATE] datetime NULL ,
	[UPDATED_USER_ID] int NULL ,
	[STATUS] tinyint NOT NULL DEFAULT ((1)) 
)
GO
CREATE TABLE [TEMPLATE_FILE] (
	[TEMPLATE_FILE_ID] int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	[TEMPLATE_FILE_NAME] nvarchar(250) NULL ,
	[TEMPLATE_FILE_PATH] nvarchar(550) NULL,
	[CREATED_DATE] datetime NULL DEFAULT (getdate()) ,
	[CREATED_USER_ID] int NULL
)
GO
CREATE TABLE [CONTRACT] (
	[CONTRACT_ID] int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	[CONTRACT_CODE] nvarchar(250) NULL , --Số hợp đồng
	[CONTRACT_DATE_SIGNED] datetime NULL , --Ngày ký
	[CONTRACT_DATE_START] datetime NULL , --Ngày bắt đầu
	[CONTRACT_DURATION] datetime NULL , --Thời gian thực hiện hợp đồng
	[CONTRACT_DATE_EXPECTED_COMPLETION] datetime NULL, --Ngày hoàn thành dự kiến
	[CONTRACT_DATE_LIQUIDATION] datetime NULL, --Ngày thanh lý
	[CONTRACT_NAME_INVESTMENT_MANANGER] nvarchar(250) NULL, --Tên chủ quản đầu tư
	[CONTRACT_NAME_OWNER] nvarchar(250) NULL, --Tên chủ đầu tư
	[CONTRACT_NUMBER_SELECTION_PLAN] nvarchar(250) NULL, --Số KẾ HOẠCH LỰA CHỌN NHÀ THẦU
	[CONTRACT_NUMBER_TENDER_NOTICE] nvarchar(250) NULL, --Số THÔNG BÁO MỜI THẦU
	[CONTRACT_CONTENT_SELECTION_PLAN] nvarchar(250) NULL, --Văn bản phê duyệt KẾ HOẠCH LỰA CHỌN NHÀ THẦU
	[CONTRACT_NUMBER_ESTABLISHIND_EXPERT] nvarchar(250) NULL, --Số QĐ Thành lập tổ chuyên gia
	[CONTRACT_NUMBER_REPORT] nvarchar(250) NULL, --Số báo cáo
	[CONTRACT_DATE_REPORT] datetime NULL, --Ngày báo cáo
	[CONTRACT_NAME_PACKAGE] datetime NULL, --Tên gói thầu
	[CONTRACT_CONTENT_CONSULTING_WORK] nvarchar(250) NULL, --Nội dung công việc tư vấn
	[CONTRACT_VALUE_BIDDING_PACKAGE] nvarchar(250) NULL, --Trị giá gói thầu (VND)
	[CONTRACT_NUMBER_BILLS] nvarchar(250) NULL, --Số hóa đơn
	[CONTRACT_DATE_EXPORT_BILLS] datetime NULL, --Ngày xuất hóa đơn
	[CONTRACT_NOTE] nvarchar(250) NULL, --Số hóa đơn
	[CONTRACT_VALUE_CONSULTING] nvarchar(250) NULL, --Trị giá HĐTV (VND)
	[CONTRACT_INFORMATION] nvarchar(250) NULL, --Thông tin liên hệ
	[CONTRACT_PERSON_CHARGE] int NULL, --Người phụ trách
	[CREATED_DATE] datetime NULL DEFAULT (getdate()) ,
	[CREATED_USER_ID] int NULL ,
	[UPDATED_DATE] datetime NULL ,
	[UPDATED_USER_ID] int NULL ,
	[STATUS] tinyint NOT NULL DEFAULT ((1)) --Tình trạng thanh toán
)
GO
CREATE TABLE [USER_GROUP] (
	[USER_GROUP_ID] int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	[USER_GROUP_NAME] nvarchar(250) NULL ,
	[CREATED_DATE] datetime NULL DEFAULT (getdate()) ,
	[CREATED_USER_ID] int NULL ,
	[UPDATED_DATE] datetime NULL ,
	[UPDATED_USER_ID] int NULL ,
	[STATUS] tinyint NOT NULL DEFAULT ((1)) 
)
GO
CREATE TABLE [USER] (
	[USER_ID] int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	[USER_GROUP_ID] int NULL ,
	[USERNAME] varchar(550) NULL ,
	[PASSWORD] varchar(550) NULL ,
	[EMAIL] varchar(550) NULL ,
	[PHONE] varchar(550) NULL ,
	[FULL_NAME] nvarchar(250) NULL ,
	[ADDRESS] nvarchar(250) NULL ,
	[CREATED_DATE] datetime NULL DEFAULT (getdate()) ,
	[CREATED_USER_ID] int NULL ,
	[UPDATED_DATE] datetime NULL ,
	[UPDATED_USER_ID] int NULL ,
	[STATUS] tinyint NOT NULL DEFAULT ((1)) 
)
GO
CREATE TABLE [USER_PERMISSION] (
	[USER_PERMISSION_ID] int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	[USER_GROUP_ID] int NULL ,
	[MODULE_ID] int NULL 
)
GO
CREATE TABLE [dbo].[MODULE] (
	[MODULE_ID] int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	[MODULE_NAME] nvarchar(250) NULL ,
	[MODULE_URL] varchar(250) NULL ,
	[CREATED_DATE] datetime NULL DEFAULT (getdate()) ,
	[CREATED_USER_ID] int NULL 
)
GO
CREATE TABLE [dbo].[SETTING] (
	[SETTING_KEY] varchar(250) NOT NULL PRIMARY KEY,
	[SETTING_VALUE] nvarchar(250) NULL ,
	[SETTING_GROUP] varchar(250) NULL ,
	[CREATED_DATE] datetime NULL DEFAULT (getdate()) ,
	[CREATED_USER_ID] int NULL ,
	[UPDATED_DATE] datetime NULL ,
	[UPDATED_USER_ID] int NULL ,
	[STATUS] tinyint NOT NULL DEFAULT ((1)) 
)