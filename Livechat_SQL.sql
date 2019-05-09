CREATE DATABASE LiveChat;
GO

USE [LiveChat];
GO

CREATE TABLE MessageDetail (
    MessageID INT PRIMARY KEY IDENTITY(1,1),
    FromUser NVARCHAR(255) NOT NULL,
    ToUser NVARCHAR(255) NOT NULL,
    DateCreated DATETIME,
    [Content] NVARCHAR(MAX),
    MessageType NVARCHAR(255)
);
GO

CREATE TABLE Users (
    UserName NVARCHAR(255) PRIMARY KEY,
    DisplayName NVARCHAR(MAX),
);
GO

SELECT * FROM dbo.MessageDetail;
GO

SELECT * FROM dbo.Users;
GO