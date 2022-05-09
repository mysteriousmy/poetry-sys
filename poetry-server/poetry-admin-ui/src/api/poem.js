import service from "../utils/request";

export function FindAllpoem() {
  return service({
    url: "/admins/poems",
    method: "get",
  });
}

export function Savepoem(data) {
  return service({
    url: "/admins/poems",
    method: "post",
    data: data,
  });
}
export function Updatepoem(data) {
  return service({
    url: "/admins/poems",
    method: "put",
    data: data,
  });
}
export function DeletepoemById(id) {
  return service({
    url: "/admins/poems/" + id,
    method: "delete",
  });
}
