create view Decrypt_Promise_Budget as

SELECT GL_CODE
      ,GL_NAME
      ,GL_GROUP_CODE
      ,GL_GROUP_NAME
      ,PROKER_ID
      ,COST_CENTER
      ,BRANCH
      ,CURRENCY
      ,cast(DECRYPTBYPASSPHRASE('uncal',MIN_AMOUNT) as varchar) MIN_AMOUNT
      ,cast(DECRYPTBYPASSPHRASE('uncal',MAX_AMOUNT) as varchar) MAX_AMOUNT
      ,BUDGET_YEAR
      ,BUDGET_MONTH
      ,cast(DECRYPTBYPASSPHRASE('uncal',RESERVED_AMOUNT) as varchar) RESERVED_AMOUNT
      ,cast(DECRYPTBYPASSPHRASE('uncal',REALIZATION) as varchar) REALIZATION
      ,cast(DECRYPTBYPASSPHRASE('uncal',REMAINING_BUDGET) as varchar) REMAINING_BUDGET
  FROM Promise_Budget