import service from "../utils/request";

export function FindAllUser() {
  return service({
    url: "/admin/users",
    method: "get",
  });
}

export function SaveUser(data) {
  return service({
    url: "/admin/users",
    method: "post",
    data: data,
  });
}
export function UpdateUser(data) {
  return service({
    url: "/admin/users",
    method: "put",
    data: data,
  });
}
export function DeleteUserById(id) {
  return service({
    url: "/admin/users?id=" + id,
    method: "delete",
  });
}
