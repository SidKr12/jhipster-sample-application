import { Moment } from 'moment';

export interface IGeoFence {
  id?: number;
  fenceID?: number;
  fenceName?: string;
  fenceCode?: string;
  type?: number;
  createdBy?: string;
  createdTime?: Moment;
  modifiedBy?: string;
  modifiedTime?: Moment;
}

export class GeoFence implements IGeoFence {
  constructor(
    public id?: number,
    public fenceID?: number,
    public fenceName?: string,
    public fenceCode?: string,
    public type?: number,
    public createdBy?: string,
    public createdTime?: Moment,
    public modifiedBy?: string,
    public modifiedTime?: Moment
  ) {}
}
