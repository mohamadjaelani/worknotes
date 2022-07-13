-- get list storeprcedure
Select [NAME] from sysobjects where type = 'P' and category = 0
-- get list view
Select [NAME] from sysobjects where type = 'V' and category = 0
-- get list table
SELECT TABLE_NAME  FROM INFORMATION_SCHEMA.TABLES


--- untuk ngeget parameter di store procedure
select  
   'Parameter_name' = name,  
   'Type'   = type_name(user_type_id),  
   'Length'   = max_length,  
   'Prec'   = case when type_name(system_type_id) = 'uniqueidentifier' 
              then precision  
              else OdbcPrec(system_type_id, max_length, precision) end,  
   'Scale'   = OdbcScale(system_type_id, scale),  
   'Param_order'  = parameter_id,  
   'Collation'   = convert(sysname, 
                   case when system_type_id in (35, 99, 167, 175, 231, 239)  
                   then ServerProperty('collation') end)  

  from sys.parameters where object_id = object_id('SP_Promise_Budget') -- SP_Promise_Budget -> nama store procedure