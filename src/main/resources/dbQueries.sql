--DROP TABLE IF EXISTS STOCK_TICKER;

CREATE TABLE STOCK_TICKER (
  id BIGSERIAL not null,
  quarter varchar(255),
  stock varchar(255),
  date varchar(255),
  open varchar(255),
  high varchar(255),
  low varchar(255),
  close varchar(255),
  volume varchar(255),
  percent_change_price varchar(255),
  percent_change_volume_over_last_week varchar(255),
  previous_weeks_volume varchar(255),
  next_weeks_open varchar(255),
  next_weeks_close varchar(255),
  percent_change_next_weeks_price varchar(255),
  days_to_next_dividend varchar(255),
  percent_return_next_dividend varchar(255),
  primary key (id)
);

