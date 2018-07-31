## find the newest version in a table
```
create table user (
    id varchar(5), 
    version integer, 
    reversion integer
);
insert into user(id, version, reversion) values("a",1,0);
insert into user(id, version, reversion) values("a",1,1);
insert into user(id, version, reversion) values("b",1,0);
insert into user(id, version, reversion) values("b",1,1);
insert into user(id, version, reversion) values("b",2,0);

create view temp as (
select id, max(version) as v
from user
group by id
);

select temp.id, temp.v, max(reversion) as rv
from user, temp
where user.id = temp.id and user.version = temp.v
group by temp.id
```

## delete duplicate rows in sql
```
With EmployeeCTE As (
    select *, ROW_NUMBER() OVER(Partition BY ID order By ID) as RowNumber
    from Employee
)
Delete from Employee where RowNumber > 1
```
