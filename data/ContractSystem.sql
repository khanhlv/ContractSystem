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
	[CONTRACT_CODE] nvarchar(250) NULL ,
	[CREATED_DATE] datetime NULL DEFAULT (getdate()) ,
	[CREATED_USER_ID] int NULL ,
	[UPDATED_DATE] datetime NULL ,
	[UPDATED_USER_ID] int NULL ,
	[STATUS] tinyint NOT NULL DEFAULT ((1)) 
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