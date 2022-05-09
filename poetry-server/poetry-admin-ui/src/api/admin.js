import service from "../utils/request";

export function IsInit() {
  return service({
    url: "/admins/hasInit",
    method: "get",
  });
}

export function Init(data) {
  return service({
    url: "/admins/init",
    method: "post",
    data: data,
  });
}

export function DeleteadminsById(id) {
  return service({
    url: "/admins/" + id,
    method: "delete",
  });
}

export function FindAlladmins() {
  return service({
    url: "/admins",
    method: "get",
  });
}

export function Saveadmins(data) {
  return service({
    url: "/admins",
    method: "post",
    data: data,
  });
}
export function Updateadmins(data) {
  return service({
    url: "/admins",
    method: "put",
    data: data,
  });
}
export function AdminSendEmail(email) {
  return service({
    url: "/admins/pubEmail?email=" + email,
    method: "get",
  });
}

export function AdminLogin(data) {
  return service({
    url: "/admins/login",
    method: "post",
    data: data,
  });
}

export function AdminEmailLogin(data) {
  return service({
    url: "/admins/emailLogin",
    method: "post",
    data: data,
  });
}
